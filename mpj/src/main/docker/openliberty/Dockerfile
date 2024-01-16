FROM jre-11:1.0
LABEL maintainer="Rinus Hofman" description="OpenLiberty Development Builds"

# Install OpenLiberty
ENV VERSION 23.0.0.12
ENV INSTALL_DIR /opt
ENV CONFIG ${INSTALL_DIR}/wlp/usr/servers/defaultServer
ENV DEPLOYMENT_DIR ${CONFIG}/dropins
RUN apk add --update curl \
    && curl -O https://public.dhe.ibm.com/ibmdl/export/pub/software/openliberty/runtime/release/${VERSION}/openliberty-${VERSION}.zip \
    && unzip openliberty-${VERSION}.zip -d ${INSTALL_DIR} \
    && /opt/wlp/bin/server create defaultServer \
    && rm openliberty-${VERSION}.zip


# Define user and group
ARG UID=1001
ARG GID=1000
ENV GNAME=appgroup
ENV UNAME=appuser

RUN addgroup -g "$GID" "$GNAME" && \
    adduser -u "$UID" --disabled-password -G "$GNAME" -s /bin/sh -h ${INSTALL_DIR} $UNAME

RUN chown -R "$UID":"$GID" ${INSTALL_DIR}/wlp \
    && chmod -R a+rw ${INSTALL_DIR}/wlp
USER "$UNAME"

# Entry point
EXPOSE 9080 9443
ENTRYPOINT /opt/wlp/bin/server run defaultServer
