FROM node:alpine AS build

WORKDIR /app

COPY package.json .

RUN npm install

COPY . .

RUN npm run build



FROM node:alpine

WORKDIR /app

COPY --from=build /app/dist/ /app/dist/

EXPOSE 8081

COPY package.json .

RUN npm install typescript

EXPOSE 8081

CMD ["npm", "run", "prod"]