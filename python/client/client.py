import openapi_client
from openapi_client.rest import ApiException
from pprint import pprint

import json
import requests
import time

def nd_line_generator():
    """
    Newline delimited json generator
    """
    for i in range(10000):
        time.sleep(1)
        print ("Sending :" + str(i))
        yield json.dumps({"id": i, "name": "element"}, separators=(',', ':')) + "\n"

# Default headers for NDJSON
default_headers = {"Content-Type": "application/x-ndjson"}

try:
    response = requests.post("http://localhost:8080/streamndJson",
                             data=nd_line_generator(),
                             headers=default_headers, stream=True)
    response.raise_for_status()  # Raise HTTPError for bad responses
    print(response.content)
except requests.exceptions.RequestException as e:
    print(f"Request failed: {e}")
