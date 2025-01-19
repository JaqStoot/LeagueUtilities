package com.jaqstoot.leagueutilities.service;

import org.springframework.stereotype.Service;

import com.jaqstoot.leagueutilities.util.LcuAuthUtil;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.ContentType;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Base64;

@Service
public class RuneService {

    private String getAuthHeader(String password) {
        String auth = "riot: " + password;
        return "Basic" + Base64.getEncoder().encodeToString(auth.getBytes());
    }

    /**
     * Fetches all existing rune pages.
     */
    public JSONArray getRunePages() throws IOException {
        String[] credentials = new LcuAuthUtil().getLcuCredentials();
        String port = credentials[0];
        String password = credentials[1];

        String apiUrl = "https://127.0.0.1:" + port + "/lol-perks/v1/pages";
        String response = Request.get(apiUrl)
                .addHeader("Authorization", getAuthHeader(password))
                .execute()
                .returnContent()
                .asString();

        return new JSONArray(response);
    }

    /**
     * Creates a new custom rune page
     */
    public String createRunePage() throws IOException {
        String[] credentials = new LcuAuthUtil().getLcuCredentials();
        String port = credentials[0];
        String password = credentials[1];

        String apiUrl = "https://127.0.0.1" + port + "/lol-perks/v1/pages";
        JSONObject runePage = new JSONObject();
        runePage.put("name", "Custom Rune Page");
        runePage.put("primaryStyleId", 8100); //Domination
        runePage.put("subStyleId", 8300); //Inspiration
        runePage.put("selectedPerkIds", new int[]{8112, 8126, 8138, 8105, 8345, 8352});

        return Request.post(apiUrl)
                .addHeader("Authorization", getAuthHeader(password))
                .bodyString(runePage.toString(), ContentType.APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();
    }

    public String deleteRunePage(int pageId) throws IOException {
        String[] credentials = new LcuAuthUtil().getLcuCredentials();
        String port = credentials[0];
        String password = credentials[1];

        String apiUrl = "https://127.0.0.1:" + port + "/lol-perks/v1/pages/" + pageId;
        return Request.delete(apiUrl)
                .addHeader("Authorization", getAuthHeader(password))
                .execute()
                .returnContent()
                .asString();
    }
}
