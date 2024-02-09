FROM ubuntu:23.10

RUN apt-get update && apt-get install -y unzip python3 python3-pip
RUN pip3 install torch==2.0.1 --index-url https://download.pytorch.org/whl/cpu \
--break-system-packages
RUN pip3 install grpcio==1.58.0 grpcio-tools==1.58.0 numpy --break-system-packages

COPY . .

EXPOSE 5440

CMD ["python3","server.py"]