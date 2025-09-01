#!/bin/bash

# Base URL
BASE_URL="http://localhost:8082/books"

# Sample titles
TITLES=("Journey to Mars" "Ocean of Time" "Quantum Dawn" "Lost Empires" "Shadow Run"
        "Chronicles of Future" "Whispers of Code" "Last Algorithm" "Beyond Gravity" "The Singularity Rift")

# Sample categories
CATEGORIES=("Sci-Fi" "Fantasy" "History" "Technology" "Mystery")

# Sample editions
EDITIONS=("1st Edition" "2nd Edition" "Revised" "Deluxe" "Collector")

# Sample regions and prices
REGIONS=("US" "UK" "IN" "EU")

# Author IDs (you already created 1-10)
AUTHOR_IDS=(1 2 3 4 5 6 7 8 9 10)

# Create 10 books
for i in {0..9}; do
  TITLE="${TITLES[$i]}"
  CATEGORY="${CATEGORIES[$RANDOM % ${#CATEGORIES[@]}]}"
  EDITION="${EDITIONS[$RANDOM % ${#EDITIONS[@]}]}"
  AUTHOR1=${AUTHOR_IDS[$RANDOM % ${#AUTHOR_IDS[@]}]}
  AUTHOR2=${AUTHOR_IDS[$RANDOM % ${#AUTHOR_IDS[@]}]}

  # Random pricePerRegion JSON
  PRICE_JSON="{\"US\": $(($RANDOM % 20 + 10)).99, \"UK\": $(($RANDOM % 15 + 8)).49, \"IN\": $(($RANDOM % 400 + 200)).00}"

  JSON=$(cat <<EOF
{
  "title": "$TITLE",
  "authors": [$AUTHOR1, $AUTHOR2],
  "category": "$CATEGORY",
  "edition": "$EDITION",
  "pricePerRegion": $PRICE_JSON,
  "publisherId": "pub-001",
  "approved": false
}
EOF
)

  echo "📘 Creating Book: $TITLE"
  curl -s -X POST "$BASE_URL" \
    -H "Content-Type: application/json" \
    -d "$JSON" | jq '.id, .title'

done
