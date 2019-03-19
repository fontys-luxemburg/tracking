FROM payara/server-full
COPY /target/tracking.war $DEPLOY_DIR
