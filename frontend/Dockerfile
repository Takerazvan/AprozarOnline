FROM node:14.2.0-alpine3.10

WORKDIR /app
COPY package*.json ./
RUN npm install && npm audit fix
COPY . .
EXPOSE 3000


CMD ["npm","run","dev"]