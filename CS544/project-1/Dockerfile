FROM  ubuntu:23.10

RUN apt-get update && apt-get install -y unzip
RUN apt-get install -y wget
COPY count.sh /count.sh
RUN chmod u+x count.sh
CMD ["/count.sh"]
