package com.example.erick.aondeir;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carolina, Erick e Isabela on 10/09/2017.
 */

public class Connection {

    private static final String ns = null;
    private  String inicialUrl = "https://api.catracalivre.com.br/select/?fq={!geofilt%20pt=";
    private  String finallUrl = "%20sfield=place_geolocation%20d=3}&q=post_type:event";
    private  String geoLocation = "-23.17944,-45.88694";

    public List listDoc(){

        URL urlConection;
        HttpURLConnection urlConnection = null;
        List<Doc> xmlList = null;

        try {
            urlConection = new URL(inicialUrl + geoLocation + finallUrl);
            urlConnection = (HttpURLConnection) urlConection.openConnection();
            InputStream in = urlConnection.getInputStream();
            xmlList = parse(in);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                urlConnection.disconnect();
            } catch (Exception e) {
                e.printStackTrace(); //If you want further info on failure...
            }
        }
        return xmlList;
    }

    public String getCityName(String geoLoc){
        return geoLoc;
    }

    // ****************************PARSING***********************************************

    public List parse(InputStream in) throws XmlPullParserException, IOException {

        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            List resultList = readResult(parser);
            return resultList;
        } finally {
            in.close();
        }
    }
    private List readResult(XmlPullParser parser) throws XmlPullParserException, IOException {
        List docs = new ArrayList();
        parser.require(XmlPullParser.START_TAG, ns, "response");
        while (parser.next() != XmlPullParser.END_DOCUMENT) {
            String name = parser.getName();
            // Starts by looking for the doc tag
            try{
                if (name.equals("doc")) {
                    docs.add(readDoc(parser));
                }
            }catch (Exception e){}
        }
        return docs;
    }
    private Doc readDoc(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "doc");
        // **************Ok!
        String post_title = null;
        String post_image_thumbnail = null;
        String place_name = null;
        String place_geolocation = null;
        String place_neighborhood = null;

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            String attribute = parser.getAttributeValue(0);
            if (name.equals("str") && attribute.equals("post_title")) {
                post_title = readPost_title(parser);
            }else if (name.equals("str") && attribute.equals("post_image_thumbnail")) {
                post_image_thumbnail = readPost_image_thumbnail(parser);
            }else if (name.equals("arr") && attribute.equals("place_name")) {
                place_name = readPlace_name(parser);
                //System.out.println("teste readPlace_name!" + place_name);
            }else if (name.equals("arr") && attribute.equals("place_geolocation")) {
                place_geolocation = readPlace_geolocation(parser);
            }else if (name.equals("arr") && attribute.equals("place_neighborhood")) {
                place_neighborhood = readPlace_neighborhood(parser);
            }else {
                skip(parser);
            }
        }
        return new Doc(post_title, post_image_thumbnail, place_name, place_geolocation, place_neighborhood);
    }
    private String readPost_title(XmlPullParser parser) throws IOException, XmlPullParserException {
        String title = "";
        parser.require(XmlPullParser.START_TAG, ns, "str");
        String tag = parser.getName();
        String attribute = parser.getAttributeValue(0);
        if (tag.equals("str")) {
            if (attribute.equals("post_title")){
                title = readText(parser);
            }
        }
        parser.require(XmlPullParser.END_TAG, ns, "str");
        //System.out.println("teste readPost_title!" + title);
        return title;
    }
    private String readPost_image_thumbnail(XmlPullParser parser) throws IOException, XmlPullParserException {
        String image_thumbnail = "";
        parser.require(XmlPullParser.START_TAG, ns, "str");
        String tag = parser.getName();
        String attribute = parser.getAttributeValue(0);
        if (tag.equals("str")) {
            if (attribute.equals("post_image_thumbnail")){
                image_thumbnail = readText(parser);
            }
        }
        parser.require(XmlPullParser.END_TAG, ns, "str");
        //System.out.println("teste readpost_image_thumbnail!" + image_thumbnail);
        return image_thumbnail;
    }
    private String readPlace_name(XmlPullParser parser) throws IOException, XmlPullParserException {
        String place_name = "";
        parser.require(XmlPullParser.START_TAG, ns, "arr");
        parser.next();
        String tag = parser.getName();
        if (tag.equals("str")) {
            place_name = readText(parser);
            parser.next();
            // *** if parser.next is null is because there are a lot of places... ***
            parser.require(XmlPullParser.END_TAG, ns, "arr");
            return place_name;
        }else {
            parser.next();
            parser.require(XmlPullParser.END_TAG, ns, "arr");
            return "will be implemented...";
        }
        //System.out.println("teste readPlace_name!" + place_name);

    }
    private String readPlace_geolocation(XmlPullParser parser) throws IOException, XmlPullParserException {
        String place_geolocation = "";
        parser.require(XmlPullParser.START_TAG, ns, "arr");
        parser.next();
        String tag = parser.getName();
        if (tag.equals("str")) {
            place_geolocation = readText(parser);
        }
        parser.next();
        parser.require(XmlPullParser.END_TAG, ns, "arr");
        //System.out.println("teste readPlace_geolocation!" + place_geolocation);
        return place_geolocation;
    }
    private String readPlace_neighborhood(XmlPullParser parser) throws IOException, XmlPullParserException {
        String place_neighborhood = "";
        parser.require(XmlPullParser.START_TAG, ns, "arr");
        parser.next();
        String tag = parser.getName();
        if (tag.equals("str")) {
            place_neighborhood = readText(parser);
        }
        parser.next();
        parser.require(XmlPullParser.END_TAG, ns, "arr");
        //System.out.println("teste readPlace_neighborhood!" + place_neighborhood);
        return place_neighborhood;
    }
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }
    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}