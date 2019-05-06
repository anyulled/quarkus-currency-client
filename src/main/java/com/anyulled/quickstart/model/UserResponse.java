package com.anyulled.quickstart.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private String id;
    private String username;
    private String fullName;
    private String profilePicture;
    private String bio;
    private String website;
    private boolean isBusiness;
    private Counts counts;

    @Data
    @Builder
    private static class Counts {
        private int media;
        private int follows;
        private int followedBy;
    }
}
