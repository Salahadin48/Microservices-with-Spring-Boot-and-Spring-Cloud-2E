package com.vizai.microservices.core.recommendation.services;

import com.vizai.api.core.recommendation.Recommendation;
import com.vizai.api.core.recommendation.RecommendationService;
import com.vizai.api.exceptions.InvalidInputException;
import com.vizai.util.http.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RecommendationServiceImpl implements RecommendationService {
    private static final Logger LOG = LoggerFactory.getLogger(RecommendationServiceImpl.class);

    private final ServiceUtil serviceUtil;

    @Autowired
    public RecommendationServiceImpl(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }

    @Override
    public List<Recommendation> getRecommendations(int productId) {
        if(productId < 1) throw new InvalidInputException("Invalid productId: "+productId);
        if(productId == 113){
            LOG.debug("No recommendation found for productId: {}", productId);
            return new ArrayList<>();
        }
        List<Recommendation> recommendations = new ArrayList<>();
        recommendations.add(new Recommendation(productId, 1, "Author-1", 1, "Content-1", serviceUtil.getServiceAddress()));
        recommendations.add(new Recommendation(productId, 2, "Author-2", 2, "Content-2", serviceUtil.getServiceAddress()));
        recommendations.add(new Recommendation(productId, 3, "Author-3", 3, "Content-3", serviceUtil.getServiceAddress()));
        LOG.debug("/Recommendations response size: {}", recommendations.size());
        return recommendations;
    }
}
