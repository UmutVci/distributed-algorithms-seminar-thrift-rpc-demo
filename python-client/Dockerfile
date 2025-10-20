FROM python:3.11-slim

WORKDIR /app

COPY python-client/client.py /app/client.py
COPY gen-py /app/gen-py

RUN pip install thrift

CMD ["python", "client.py"]
