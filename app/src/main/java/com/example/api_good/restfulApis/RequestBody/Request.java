package com.example.api_good.restfulApis.RequestBody;


import java.util.List;

public class Request {

    private String api_token;

    private String email;

    private String password;

    private String fullname;

    private String username;

    private String secret_key;

    private String secretKey;

    private String phoneCode;

    private String phone;

    private String comment;

    private String display_name;

    private int user_id;

    private int product_id;

    private int category_id;

    private String slug;

    private float rate;

    //private List<product> products;

    //private List<address> address;

    private double coupon_amount;

    private int order_id;

    private String order_process_key;

    private String coupon_code;

    private String order_note;

    private String shipping_method;

    private double shipping_cost;

    private String payment_method_title;

    private String payment_method;

    private Boolean is_coupon_applyed;

    private String search_term;
    private String selected_sizes;
    private String selected_colors;
    private String sort_by;
    private double price_max;
    private double price_min;
    private Double total;

    private String account_bill_first_name;
    private String account_bill_last_name;
    private String account_bill_email_address;
    private String account_bill_phone_number;
    private String account_bill_address_line_1;
    private String account_shipping_first_name;
    private String account_shipping_last_name;
    private String account_shipping_email_address;
    private String account_shipping_phone_number;
    private String account_shipping_address_line_1;

    public Request(String email) { // ForgetPassword
        this.email = email;
    }

    public Request(int user_id, String api_token) { // getAllProducts
        this.user_id = user_id;
        this.api_token = api_token;
    }

    public Request(String username, String password) { // login Request
        this.username = username;
        this.password = password;
    }

    public Request(String api_token, String email, String display_name, int user_id) { // Update user Profile Data
        this.api_token = api_token;
        this.email = email;
        this.display_name = display_name;
        this.user_id = user_id;
    }

    public Request(String email, String password, String fullname, String username) { // Register
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.username = username;
//        this.phoneCode = phoneCode;
//        this.phone = phone;
    }

    public Request(int user_id, String api_token, int product_id) { // Add - Remove Product From Favorite && getProductById && getProductBycategoryID
        this.user_id = user_id;
        this.api_token = api_token;
        this.product_id = product_id;
        this.category_id = product_id;
    }

    public Request(int user_id, String api_token, String slug) {  // getProductBySlug
        this.user_id = user_id;
        this.api_token = api_token;
        this.slug = slug;
        this.category_id = product_id;
    }

    public Request(int user_id, String api_token, int product_id, String comment, float rate) { // Rate and comment a Product
        this.user_id = user_id;
        this.api_token = api_token;
        this.product_id = product_id;
        this.comment = comment;
        this.rate = rate;
    }

   /* // set User Order
    public Request(String api_token, int user_id, List<product> products, double coupon_amount, String coupon_code, String order_note, String shipping_method, double shipping_cost, String payment_method_title, String payment_method, Boolean is_coupon_applyed, Double total) {
        this.api_token = api_token;
        this.user_id = user_id;
        this.products = products;
        this.coupon_amount = coupon_amount;
        this.coupon_code = coupon_code;
        this.order_note = order_note;
        this.shipping_method = shipping_method;
        this.shipping_cost = shipping_cost;
        this.payment_method_title = payment_method_title;
        this.payment_method = payment_method;
        this.is_coupon_applyed = is_coupon_applyed;
        this.total = total;
    }

    // set Guest Order
    public Request(String api_token, int user_id, List<product> products, List<address> address , double coupon_price, String coupon_code, String order_note, String shipping_method, double shipping_cost, String payment_method_title, String payment_method, Boolean is_coupon_applyed, Double total) {
        this.api_token = api_token;
        this.user_id = user_id;
        this.products = products;
        this.address = address;
        this.coupon_amount = coupon_price;
        this.coupon_code = coupon_code;
        this.order_note = order_note;
        this.shipping_method = shipping_method;
        this.shipping_cost = shipping_cost;
        this.payment_method_title = payment_method_title;
        this.payment_method = payment_method;
        this.is_coupon_applyed = is_coupon_applyed;
        this.total = total;
    }

//    Check Coupon Code
    public Request(String api_token, int user_id, String coupon_code) {
        this.api_token = api_token;
        this.user_id = user_id;
        this.coupon_code = coupon_code;
    }

    public Request(String api_token, int user_id, String search_term, String selected_sizes, String selected_colors, String sort_by, double price_max, double price_min) { // Search
        this.api_token = api_token;
        this.user_id = user_id;
        this.search_term = search_term;
        this.selected_sizes = selected_sizes;
        this.selected_colors = selected_colors;
        this.sort_by = sort_by;
        this.price_max = price_max;
        this.price_min = price_min;
    }

    public Request(int user_id, String api_token, int order_id, String order_process_key) { // get Order Details
        this.user_id = user_id;
        this.api_token = api_token;
        this.order_id = order_id;
        this.order_process_key = order_process_key;
    }

    // update user address
    public Request(String api_token, int user_id, String account_bill_first_name, String account_bill_last_name, String account_bill_email_address, String account_bill_phone_number, String account_bill_address_line_1, String account_shipping_first_name, String account_shipping_last_name, String account_shipping_email_address, String account_shipping_phone_number, String account_shipping_address_line_1) {
        this.api_token = api_token;
        this.user_id = user_id;
        this.account_bill_first_name = account_bill_first_name;
        this.account_bill_last_name = account_bill_last_name;
        this.account_bill_email_address = account_bill_email_address;
        this.account_bill_phone_number = account_bill_phone_number;
        this.account_bill_address_line_1 = account_bill_address_line_1;
        this.account_shipping_first_name = account_shipping_first_name;
        this.account_shipping_last_name = account_shipping_last_name;
        this.account_shipping_email_address = account_shipping_email_address;
        this.account_shipping_phone_number = account_shipping_phone_number;
        this.account_shipping_address_line_1 = account_shipping_address_line_1;
    }*/
}