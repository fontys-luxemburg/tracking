FROM payara/micro:5-SNAPSHOT
RUN wget -O $PAYARA_PATH/database-connector.jar https://jdbc.postgresql.org/download/postgresql-42.2.5.jar
COPY /target/tracking.war $DEPLOY_DIR
ENTRYPOINT ${PAYARA_PATH}/generate_deploy_commands.sh && echo 'create-jdbc-connection-pool --datasourceclassname org.postgresql.ds.PGConnectionPoolDataSource --restype javax.sql.ConnectionPoolDataSource --property user=Luxemburg-tracking:password=:Luxemburg-tracking=fin_dev:ServerName=postgresql:port=5432 fing-pool' > mycommands.asadmin && cat ${DEPLOY_COMMANDS} >> mycommands.asadmin && ${PAYARA_PATH /bin/asadmin start-domain -v --postbootcommandfile mycommands.asadmin ${PAYARA_DOMAIN}'
ENTRYPOINT ["java", "-jar", "payara-micro.jar", "--addJars", "database-connector.jar", "--deploy", "$DEPLOY_DIR/tracking.war"]
