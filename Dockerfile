FROM payara/server-full:latest
COPY tracking/target/tracking.war $DEPLOY_DIR
