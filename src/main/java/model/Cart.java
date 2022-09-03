package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart {
    List<CartItems> cartItems;
    List<OutOfSale> outOfSale;
    String gifts;
    String promoCode;
    Total total;
    String isAppliedSets;
    String isExistVetCategory;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class CartItems {
        private Product product;
        private Offer offer;
        private List<PetTags> petTags;
        private int quantity;
        private int id;
        private int price;

        @AllArgsConstructor
        @NoArgsConstructor
        @Data
        public static class Product {
            String id;
            String name;
            String previewPicture;
            String smartAvailable;
            String brand;
            int brandId;
            String images;
            String badge;
            String url;
            String primaryOfferId;
            Rating rating;
            String reviewsCount;
            String offers;
            List<String> categories;
            Description description;
            String petTagForm;
            String isNew;

            @AllArgsConstructor
            @NoArgsConstructor
            @Data
            public static class Rating {
                int value;
                int voteCount;
            }

            @AllArgsConstructor
            @NoArgsConstructor
            @Data
            public static class Description {
                String description;
            }
        }

        @AllArgsConstructor
        @NoArgsConstructor
        @Data
        public static class Offer {
            int id;
            String caption;
            String artnumber;
            String available;
            int price;
            int smartPrice;
            int smartOldPrice;
            int oldPrice;
            String discountReason;
            int priceFlag;
            int weight;
            int weightPrice;
            int bonusPetshop;
            String colorInformer;
            String dateInformer;
            DeliveryDays deliveryDays;
            SpecialCategory specialCategory;
            int remains;
            int size;
            int bonusBySmart;
            List<Image> images;


            @AllArgsConstructor
            @NoArgsConstructor
            @Data
            public static class Image {
                String type;
                String alt;
                String defaultImgSrc;
                String bigImgSrc;
                String smallImgSrc;
                String imagesArr;
                String youtubeCode;
                String gifSrc;
            }

            @AllArgsConstructor
            @NoArgsConstructor
            @Data
            public static class DeliveryDays {
                int min;
                int max;

            }

            @AllArgsConstructor
            @NoArgsConstructor
            @Data
            public static class SpecialCategory {
                String addressLocket;
                String markdown;
                String sertificate;
            }
        }


        @NoArgsConstructor
        @Data
        public static class PetTags {

        }
    }

    @NoArgsConstructor
    @Data
    public static class OutOfSale {

    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Total {
        double kilogramsWeight;
        String freeDelivery;
        int productsOldSum;
        int usedBonuses;
        int discount;
        int productsFinalSum;
        int smartPrice;
        int smartOldPrice;
        int bonusTotal;
        int weightTotal;
        double volumeTotal;
        int totalQuantity;
        int bonusPetshop;
        int certificateTotal;
        int bonusBySmart;
    }
}
