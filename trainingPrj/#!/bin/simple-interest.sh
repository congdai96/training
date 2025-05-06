#!/bin/bash

# This script calculates simple interest.

if [ $# -ne 3 ]; then
  echo "Usage: $0 <principal> <rate> <time>"
  echo "  <principal>: The initial amount of money."
  echo "  <rate>: The annual interest rate (as a decimal, e.g., 0.05 for 5%)."
  echo "  <time>: The time period in years."
  exit 1
fi

principal="$1"
rate="$2"
time="$3"

# Check if inputs are numbers with optional decimal part
if ! [[ "$principal" =~ ^[0-9]+(\.[0-9]+)?$ ]]; then
  echo "Error: Principal must be a number."
  exit 1
fi

if ! [[ "$rate" =~ ^[0-9]+(\.[0-9]+)?$ ]]; then
  echo "Error: Rate must be a number."
  exit 1
fi

if ! [[ "$time" =~ ^[0-9]+(\.[0-9]+)?$ ]]; then
  echo "Error: Time must be a number."
  exit 1
fi

# Calculate simple interest
interest=$(echo "scale=2; $principal * $rate * $time" | bc)

echo "Principal: $principal"
echo "Rate: $rate"
echo "Time: $time years"
echo "Simple Interest: $interest"

exit 0
