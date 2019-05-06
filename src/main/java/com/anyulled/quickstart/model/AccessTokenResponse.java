package com.anyulled.quickstart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenResponse {
    private String accessToken;
    private User user;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User {
        private long id;
        private String username;
        private String profilePicture;
        private String fullName;
        private String bio;
        private String website;
        private boolean isBusiness;
    }
}
