FROM redxice/payara:tracking
USER payara
COPY target/tracking.war $DEPLOY_DIR
EXPOSE 8080 4848
