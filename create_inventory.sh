#!/bin/bash

# Base URL
BASE_URL="http://localhost:8083/inventory"

# Book IDs (Assuming 1-10 already exist in books table)
BOOK_IDS=(1 2 3 4 5 6 7 8 9 10)

# Regions and Formats
REGIONS=("US" "UK" "IN")
FORMATS=("Paperback" "eBook")

for BOOK_ID in "${BOOK_IDS[@]}"; do
  REGION=${REGIONS[$RANDOM % ${#REGIONS[@]}]}
  FORMAT=${FORMATS[$RANDOM % ${#FORMATS[@]}]}
  COUNT=$(($RANDOM % 81 + 20)) # 20-100
  THRESHOLD=10
  DIGITAL=$((RANDOM % 2)) # 0 or 1
  DIGITAL_BOOL=$( [ "$DIGITAL" -eq 1 ] && echo true || echo false )

  JSON=$(cat <<EOF
{
  "bookId": "$BOOK_ID",
  "region": "$REGION",
  "format": "$FORMAT",
  "availableCount": $COUNT,
  "threshold": $THRESHOLD,
  "digitalAvailable": $DIGITAL_BOOL,
  "publisherId": "pub-001",
  "lastRestockDate": "$(date -u +"%Y-%m-%dT%H:%M:%SZ")"
}
EOF
)

  echo "📦 Adding Inventory for Book ID: $BOOK_ID ($REGION - $FORMAT)"
  curl -s -X POST "$BASE_URL/$BOOK_ID" \
    -H "Content-Type: application/json" \
    -d "$JSON" | jq '.id, .region, .format'
done
