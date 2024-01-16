FROM openliberty-full:23.0

COPY --chown=${UNAME}:${GNAME} src/main/liberty/config/server.xml ${CONFIG}

COPY --chown=${UNAME}:${GNAME} target/mpj.war ${DEPLOYMENT_DIR}
