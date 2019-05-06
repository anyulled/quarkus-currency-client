package com.anyulled.quickstart.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class MediaResponse {

    private List<Media> mediaList;

    @Data
    @Builder
    private static class Media {

        private Comment comments;
        private Caption caption;
        private Like likes;
        private String link;
        private User user;
        private LocalDate createdTime;
        private ImageSet images;
        private String type;
        private String[] tags;
        private long id;
        private Location location;

        @Data
        @Builder
        private static class Comment {
            private int count;
        }

        @Data
        @Builder
        private static class Like {
            private int count;
        }

        @Data
        @Builder
        private static class User {
            private String username;
            private String profilePicture;
            private long id;
        }

        @Data
        @Builder
        private static class ImageSet {
            private Image lowResolution;
            private Image thumbnail;
            private Image standardResolution;
        }

        @Data
        @Builder
        private static class Image {
            private String url;
            private int width;
            private int height;
        }

        @Data
        @Builder
        private static class Location {
            private String latitude;
            private String longitude;
            private long id;
            private String streetAddress;
            private String name;
        }

        @Data
        @Builder
        private static class Caption {
            private long id;
            private LocalDate createdTime;
            private String text;
        }
    }

}
