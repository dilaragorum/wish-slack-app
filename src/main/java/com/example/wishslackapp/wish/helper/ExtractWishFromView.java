package com.example.wishslackapp.wish.helper;

import com.example.wishslackapp.wish.exceptions.CannotExtractSelectedValuesException;
import com.example.wishslackapp.wish.model.Gift;
import com.example.wishslackapp.wish.model.Wish;
import com.slack.api.model.view.ViewState;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.UUID;

@Slf4j
public class ExtractWishFromView {
    public static Wish getWishFromSelectedOptions(Map<String, Map<String, ViewState.Value>> values, String actionIdForWish, String actionIdForCountry) {
        ViewState.SelectedOption wishedGiftOption = extractValues(values, actionIdForWish);

        String wishedGiftId = wishedGiftOption.getValue();
        String wishedGiftName = wishedGiftOption.getText().getText();

        ViewState.SelectedOption countryOption = extractValues(values, actionIdForCountry);

        String countryName = countryOption.getText().getText();

        return Wish.builder()
                .id(UUID.randomUUID().toString())
                .gift(Gift.builder().id(wishedGiftId).name(wishedGiftName).build())
                .receiverCountry(countryName)
                .build();
    }


    private static ViewState.SelectedOption extractValues(Map<String, Map<String, ViewState.Value>> values, String actionId) {
        var elementData = values.values().stream()
                .filter(element -> element.containsKey(actionId))
                .findFirst();

        if (elementData.isEmpty()) {
            log.info("Selected Values cannot be extracted");
            throw new CannotExtractSelectedValuesException("Selected Values cannot be extracted");
        }

        return elementData.get().get(actionId).getSelectedOption();
    }
}
