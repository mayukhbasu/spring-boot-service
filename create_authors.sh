#!/bin/bash

# Base URL for the AuthorService
BASE_URL="http://localhost:8081/authors"

# Array of author names and bios
NAMES=("Alice Walker" "Bob Marley" "Charlie Chaplin" "Diana Wynne" "Ethan Hunt" "Fatima Bhutto" "George Orwell" "Helen Keller" "Isaac Newton" "Jane Austen")
BIOS=("Novelist" "Musician" "Comedian" "Fantasy Author" "Spy Fiction" "Pakistani Writer" "Political Satirist" "Activist" "Physicist" "Classic Novelist")

# Publisher ID (static or you can randomize)
PUBLISHER_ID="pub-001"

# Track created IDs
AUTHOR_IDS=()

# Create authors
echo "Creating authors..."
for i in ${!NAMES[@]}; do
  NAME=${NAMES[$i]}
  BIO=${BIOS[$i]}
  ROYALTY=$(echo "scale=2; 5 + ($i * 0.5)" | bc)

  RESPONSE=$(curl -s -X POST "$BASE_URL" \
    -H "Content-Type: application/json" \
    -d '{
      "name": "'"$NAME"'",
      "bio": "'"$BIO"'",
      "royaltyPercentage": '"$ROYALTY"',
      "publisherId": "'"$PUBLISHER_ID"'"
    }')

  ID=$(echo $RESPONSE | jq '.id')
  echo "✅ Created Author: $NAME (ID: $ID)"
  AUTHOR_IDS+=($ID)
done

# Final output
echo ""
echo "📦 All Created Author IDs:"
for ID in "${AUTHOR_IDS[@]}"; do
  echo "- $ID"
done
