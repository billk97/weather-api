package com.example.usecases.rating;

import com.example.enums.ErrorCode;
import com.example.exceptions.IllegalArgumentExceptionWithCode;
import com.example.repository.ForecastRatingRepository;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;

@RequestScoped
public class DeleteRatingById {

    @Inject
    ForecastRatingRepository forecastRatingRepo;


    @Transactional
    public void command(String ratingId) {
        if(!StringUtils.isNumeric(ratingId)) {
            throw new IllegalArgumentExceptionWithCode(
                String.format("Location with id: %s not found", ratingId),
                ErrorCode.INVALID_INPUT
            );
        }
        forecastRatingRepo.deleteById(Long.valueOf(ratingId));
    }
}
