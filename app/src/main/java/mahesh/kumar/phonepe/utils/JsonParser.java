package mahesh.kumar.phonepe.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mahesh.kumar.phonepe.network.model.Logo;

public class JsonParser {

    /**
     * Method to get Logos from Json
     * @return
     */
    public List<Logo> getLogoList() {
        List<Logo> logos = new ArrayList<>();
        try {
            JSONArray items = new JSONArray(Constants.GAME_JSON);
            for (int i = 0; i < items.length(); i++) {
                JSONObject jsonObject = items.getJSONObject(i);
                Logo logo = new Logo();
                logo.setImgUrl(jsonObject.getString(Constants.JsonConstants.IMAGE_URL));
                logo.setName(jsonObject.getString(Constants.JsonConstants.NAME));
                logos.add(logo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return logos;
    }
}
