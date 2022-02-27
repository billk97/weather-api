package com.example.usecases.rating;

import com.example.domain.Rating;
import com.example.enums.ErrorCode;
import com.example.exceptions.IllegalArgumentExceptionWithCode;
import com.example.repository.ForecastRatingRepository;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;

@RequestScoped
public class GetRatingById {

    @Inject
    private ForecastRatingRepository forecastRatingRepository;

    public Rating query(String ratingId) {
        if(!StringUtils.isNumeric(ratingId)) {
            throw new IllegalArgumentExceptionWithCode(
                String.format("Location with id: %s not found", ratingId),
                ErrorCode.INVALID_INPUT
            );
        }
        return forecastRatingRepository.findById(Long.valueOf(ratingId));
    }
}
