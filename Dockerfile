FROM payara/micro
RUN wget -O $PAYARA_PATH/database-connector.jar https://jdbc.postgresql.org/download/postgresql-42.2.5.jar
COPY /target/tracking.war $DEPLOY_DIR
ENTRYPOINT ["java", "-jar", "$PAYARA_PATH/payara-micro.jar", "--addJars", "$PAYARA_PATH/database-connector.jar", "--deploy", "$PAYARA_PATH/myapplication.war"]
