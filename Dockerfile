FROM payara/micro
COPY /target/tracking.war $DEPLOY_DIR
