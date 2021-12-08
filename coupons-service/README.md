# coupons-service

Design an offer service for delivery service like Dunzo

### Problem Statement
- Multi-category supported (Grocery, Medical, Electronics)
- Two kinds of discount type (% off & rupees off)
- Offer is kind of two types
  * Applicable on a category (For ex: Rs 10 off on chocolates)
  * Applicable on Supermarket (10% off on More supermarket across all categories) 
- Offer is applicable to a particular city
- Offer has a validity period like start_date, end_date, start_time & end_time. For ex: 05 Sep - 25 Sep, 06:00 AM - 11:00 PM
- Offer has a max_daily_redeem_count_per_user
- Offer is returned based on user segments:
    * E.g.: parents, college_goers, parents_with_schoolchildren, smokers, alcohol_consumers
    * Assume an external API that returns the list of available segments
    * Assume an external API that takes {user_id, segment_ids} returns {segment_id: true/false} (if the user is present in the segment or not)

### Requirements

- List applicable offers API (input user details including location, return a list of applicable offers)
- Apply offer API (input cart details & offer id, return cart details with the offer applied)

### Design Considerations
LLD: API Contract, pseudo code, DB schema & queries, optimizations