import json
import random

def factory_method(id):
    price = {
        "_class": "com.udacity.pricing.domain.price.Price",
        "vehicleId": id,
        "currency": "USD",
        "price": random.randint(5000, 25000)
    }
    return price

prices = []

for i in range(1, 21):
    prices.append(factory_method(i))

# populate the database
f = open("prices.json", "w+")
f.write(json.dumps(prices))
f.close()
