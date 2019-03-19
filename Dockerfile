FROM payara/micro
RUN wget -O $PAYARA_PATH/database-connector.jar https://jdbc.postgresql.org/download/postgresql-42.2.5.jar
COPY /target/tracking.war $DEPLOY_DIR
