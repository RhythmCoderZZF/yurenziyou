package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/4/28
 * description：
 */
public class EntranceTicketResponse {

    //成人票 儿童票 家庭票
    private String ticketType;

    private String ticketPrice;

    //原价
    private String originalPrice;

    private List<TicketEntity> ticketList;

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public List<TicketEntity> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<TicketEntity> ticketList) {
        this.ticketList = ticketList;
    }

    public class TicketEntity {

        private String ticketName;

        private String bookTicketDes;

        private List<String> labelList;

        private String ticketsSoldNum;

        private String ticketPrice;

        //原价
        private String originalPrice;

        private String favorablePrice;

        public String getTicketName() {
            return ticketName;
        }

        public void setTicketName(String ticketName) {
            this.ticketName = ticketName;
        }

        public String getBookTicketDes() {
            return bookTicketDes;
        }

        public void setBookTicketDes(String bookTicketDes) {
            this.bookTicketDes = bookTicketDes;
        }

        public List<String> getLabelList() {
            return labelList;
        }

        public void setLabelList(List<String> labelList) {
            this.labelList = labelList;
        }

        public String getTicketsSoldNum() {
            return ticketsSoldNum;
        }

        public void setTicketsSoldNum(String ticketsSoldNum) {
            this.ticketsSoldNum = ticketsSoldNum;
        }

        public String getTicketPrice() {
            return ticketPrice;
        }

        public void setTicketPrice(String ticketPrice) {
            this.ticketPrice = ticketPrice;
        }

        public String getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(String originalPrice) {
            this.originalPrice = originalPrice;
        }

        public String getFavorablePrice() {
            return favorablePrice;
        }

        public void setFavorablePrice(String favorablePrice) {
            this.favorablePrice = favorablePrice;
        }
    }
}
