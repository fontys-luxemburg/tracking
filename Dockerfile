FROM payara/micro
ENTRYPOINT ${PAYARA_PATH}/generate_deploy_commands.sh && echo 'create-jdbc-connection-pool --datasourceclassname org.postgresql.ds.PGConnectionPoolDataSource --restype javax.sql.ConnectionPoolDataSource --property user=Luxemburg-tracking:password=:Luxemburg-tracking=fin_dev:ServerName=postgresql:port=5432 fing-pool' > mycommands.asadmin && cat ${DEPLOY_COMMANDS} >> mycommands.asadmin && ${PAYARA_PATH /bin/asadmin start-domain -v --postbootcommandfile mycommands.asadmin ${PAYARA_DOMAIN}'
COPY tracking/target/tracking.war $DEPLOY_DIR
