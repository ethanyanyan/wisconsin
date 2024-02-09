FROM p5-base

RUN apt-get update; apt-get install -y unzip
RUN pip3 install jupyterlab==4.0.3 jupyter-client==8.4.0 pyarrow==13.0.0 requests==2.31.0 nbconvert==7.9.2
CMD python3 -m jupyterlab --no-browser --ip=0.0.0.0 --port=5000 --allow-root --NotebookApp.token=''