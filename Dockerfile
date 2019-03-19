FROM payara/server-full:latest
COPY target/tracking.war $DEPLOY_DIR
