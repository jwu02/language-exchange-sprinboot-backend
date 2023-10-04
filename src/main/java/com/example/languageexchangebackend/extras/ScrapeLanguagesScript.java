package com.example.languageexchangebackend.extras;

import com.example.languageexchangebackend.model.Language;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScrapeLanguagesScript {
    public final String LANGUAGES_URL = "https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes";
    public final String WIKIPEDIA_URL = "https://en.wikipedia.org";

    public static void main(String[] args) {
        ScrapeLanguagesScript script = new ScrapeLanguagesScript();
        List<String> languageLinks = script.scrapeLanguageLinks();
        List<Language> scrapedLanguages = script.scrapeLanguageData(languageLinks);
        script.saveLanguagesToJSON(scrapedLanguages);

//        List<String> testList = new ArrayList<String>();
//        testList.add("/wiki/Quechuan_languages");
//        script.scrapeLanguageData(testList);
    }

    public List<String> scrapeLanguageLinks() {
        List<String> languageLinks = new ArrayList<>();

        // https://medium.com/@sushain_Dilishan/building-web-scraping-api-with-spring-boot-jsoup-a0cc19dbd5dd
        try {
            //loading the HTML to a Document Object
            Document document = Jsoup.connect(LANGUAGES_URL).get();
            // selecting the element which contains link to languages
            Elements languageRowElements = document.getElementsByTag("tbody").get(0)
                .getElementsByTag("tr");
            for (int i=1; i<languageRowElements.size(); i++) {
                Element languageNameElement = languageRowElements.get(i)
                        .getElementsByTag("td").get(0)
                        .getElementsByTag("a").get(0);
                String languageLink = languageNameElement.attr("href");

                languageLinks.add(languageLink);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return languageLinks;
    }

    public List<Language> scrapeLanguageData(List<String> languageLinks) {
        List<Language> scrapedLanguages = new ArrayList<>();

        for (String link : languageLinks) {
            try {
                String LANGUAGE_URL = WIKIPEDIA_URL + link;
                Document document = Jsoup.connect(LANGUAGE_URL).get();
                Element infoboxElement = document.getElementsByClass("infobox").first();

                String languageName = infoboxElement.getElementsByTag("tr").first().text();
                Element languageCodeElement = infoboxElement.getElementsByTag("code").first();
                if (languageCodeElement == null) {
                    languageCodeElement = infoboxElement.getElementsByTag("samp").first();
                }
                String languageCode = languageCodeElement.text();

                String nameInLanguageCssQuery = "[title$=-language text]";
                Element elementWithNameInLanguage = infoboxElement
                        .getElementsByClass("infobox-subheader")
                        .select(nameInLanguageCssQuery).first();
                String nameInLanguage;
                if (elementWithNameInLanguage == null) {
                    nameInLanguage = "";
                } else {
                    nameInLanguage = elementWithNameInLanguage.text();
                }
                nameInLanguage = sanitiseNameInLanguage(nameInLanguage);

                System.out.println(languageName+"\t"+languageCode+"\t"+nameInLanguage);
                System.out.println("--------------------------------");

                scrapedLanguages.add(new Language(languageName, languageCode, nameInLanguage));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return scrapedLanguages;
    }

    public String sanitiseNameInLanguage(String nameInLanguage) {
        // split at commas
        String processedStr = nameInLanguage.split(", ")[0];

        return processedStr;
    }

    public void saveLanguagesToJSON(List<Language> scrapedLanguages) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            String dir = "src/main/java/com/example/languageexchangebackend/extras/";
            File languagesFile = new File(dir+"languages.json");
            if (!languagesFile.exists()) {
                languagesFile.createNewFile();
            }

            FileWriter languagesFW = new FileWriter(languagesFile);
            gson.toJson(scrapedLanguages, languagesFW);
            languagesFW.flush();
            languagesFW.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Saved "+scrapedLanguages.size()+" languages.");
    }
}
