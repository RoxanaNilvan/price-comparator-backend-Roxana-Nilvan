Price Comparator Backend - Accesa Coding Challenge 2025
A Java Spring Boot backend application that helps users compare product prices across multiple stores, identify best deals, get notified when prices drop below target values, and explore price history and recommendations.

Features Implemented

   Core Business Requirements:

-Daily Shopping Basket Monitoring
  -Optimize shopping list to minimize cost, split by best store per item.
  
-Best Discounts
  -Lists products with highest percentage discounts across all stores.
  
-New Discounts
  -Filters and lists discounts added in the last 24 hours.
  
-Dynamic Price History Graph Support
  -Returns historical prices per product, filterable by store, brand, or category.
  
-Product Substitutes & Recommendations
  -Highlights best price per unit (e.g., per kg/liter), even across different pack sizes.
  
-Custom Price Alert
  -Users can set alerts for target prices and get notified when prices drop below them (alerts persisted in CSV).

   Project Structure

📁 src

├── 📁 controller         # REST endpoints

├── 📁 model              # DTOs and data models

├── 📁 service            # Business logic (CSV loading, alert checking, basket split)

├── 📁 util               # CSV parsing helpers

└── 📄 PriceComparatorApplication.java


   CSV-Based Data

Instead of using a database, the app loads products and discounts from realistic CSV files structured as follows:

  -store_YYYY-MM-DD.csv for product prices
  
  -store_discounts_YYYY-MM-DD.csv for discounts

  -alerts.csv for user-defined price alerts
  
Data is loaded and structured in memory at application start.
            

   API Endpoints Overview

  Shopping Optimization:
  
-POST /api/basket/optimize → find the store with lowest total price for the basket

-POST /api/basket/optimize/split → best store per product

  Discounts:
  
-GET /api/discounts → all discounts

-GET /api/discounts/{store} → discounts for one store

-GET /api/discounts/best?limit=n → top n discounts

-GET /api/discounts/new → new discounts from the last 24h

 Price Alerts:
 
-POST /api/alerts → add a price alert

-GET /api/alerts/triggered → products with active alerts triggered

-GET /api/alerts → list all alerts (from memory + CSV)

  Price History:
  
-GET /api/price-history/{productId} → returns historical price list for one product

-Filterable by query: ?store=kaufland, ?brand=Zuzu, etc.

 Recommendations
 
GET /api/recommendations/value-per-unit/{productId} → return alternative offers for the same product ID, sorted by value


   How to Run the App
 
Clone the repo:

git clone https://github.com/your-user/price-comparator-backend.git

Import in IntelliJ or run with Maven:

-mvn spring-boot:run

Test with Postman or curl using the documented endpoints.


   Assumptions and Notes

The app does not use a database. All data is loaded from and persisted into CSV files.

Products are assumed to match across stores via their product_id.

Alerts are stored in alerts.csv and automatically reloaded on startup.

Discounts are evaluated by current date (can be overridden in code for testability).
