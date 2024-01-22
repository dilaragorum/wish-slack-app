package com.example.wishslackapp.wish.listeners;

public class View {
    public static final String CALLBACK_ACTION_FOR_SELECT_WISH = "SELECT_WISH_MODAL";
    public static final String ACTION_ID__FOR_SELECT_WISH = "wish_select-action";
    public static final String ACTION_ID__FOR_SELECT_COUNTRY = "country_select-action";
    public static final String SELECT_WISH_VIEW = """
            {
            	"title": {
            		"type": "plain_text",
            		"text": "WHAT IS YOUR WISH!",
            		"emoji": true
            	},
            	"submit": {
            		"type": "plain_text",
            		"text": "Submit",
            		"emoji": true
            	},
            	"type": "modal",
            	"close": {
            		"type": "plain_text",
            		"text": "Cancel",
            		"emoji": true
            	},
            	"callback_id": "SELECT_WISH_MODAL",
            	"blocks": [
            		{
            			"type": "section",
            			"text": {
            				"type": "mrkdwn",
            				"text": "Select your wish, little child :)"
            			},
            			"accessory": {
            				"type": "static_select",
            				"placeholder": {
            					"type": "plain_text",
            					"text": "Wish List",
            					"emoji": true
            				},
            				"options": [
            					{
            						"text": {
            							"type": "plain_text",
            							"text": "Car",
            							"emoji": true
            						},
            						"value": "1"
            					},
            					{
            						"text": {
            							"type": "plain_text",
            							"text": "Baby",
            							"emoji": true
            						},
            						"value": "2"
            					},
            					{
            						"text": {
            							"type": "plain_text",
            							"text": "Book",
            							"emoji": true
            						},
            						"value": "3"
            					}
            				],
            				"action_id": "wish_select-action"
            			}
            		},
            		{
            			"type": "section",
            			"text": {
            				"type": "mrkdwn",
            				"text": "Please select which country you live :)"
            			},
            			"accessory": {
            				"type": "static_select",
            				"placeholder": {
            					"type": "plain_text",
            					"text": "Country List",
            					"emoji": true
            				},
            				"options": [
            					{
            						"text": {
            							"type": "plain_text",
            							"text": "England",
            							"emoji": true
            						},
            						"value": "1"
            					},
            					{
            						"text": {
            							"type": "plain_text",
            							"text": "France",
            							"emoji": true
            						},
            						"value": "2"
            					},
            					{
            						"text": {
            							"type": "plain_text",
            							"text": "Poland",
            							"emoji": true
            						},
            						"value": "3"
            					},
            					{
            						"text": {
            							"type": "plain_text",
            							"text": "China",
            							"emoji": true
            						},
            						"value": "4"
            					},
            					{
            						"text": {
            							"type": "plain_text",
            							"text": "Japan",
            							"emoji": true
            						},
            						"value": "5"
            					},
            					{
            						"text": {
            							"type": "plain_text",
            							"text": "Thailand",
            							"emoji": true
            						},
            						"value": "6"
            					},
            					{
            						"text": {
            							"type": "plain_text",
            							"text": "Turkey",
            							"emoji": true
            						},
            						"value": "7"
            					}
            				],
            				"action_id": "country_select-action"
            			}
            		}
            	]
            }
            """;
}
