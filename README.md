# RecommendProductApp backend service

### Getting started
Build the application using ``gradle build`` command. Then, run [ProductRecommendationServiceApplication.java](src%2Fmain%2Fjava%2Fcom%2Fseb%2Fvaldas%2Fproductrecommendationservice%2FProductRecommendationServiceApplication.java) via IntelliJ (recommended) or your other favorite run environment.

### Available endpoints

1) Recommend products endpoint:

``curl --request GET \
--user valdas:abc123 \
--url 'http://localhost:8080/api/products/recommend?isStudent=true&age=17&income=12000' \``

2) Add new product:

``
curl --request POST \
--url http://localhost:8080/api/products/add \
--header 'Content-Type: application/json' \
--user valdas:abc123 \
--data '{
"name": "Valdas Product",
"minAge": 18,
"minIncome": 12000,
"forStudent": false
}
``

3) Get all products


``
curl --request GET --user valdas:abc123 --url 'http://localhost:8080/api/products/all' \
``