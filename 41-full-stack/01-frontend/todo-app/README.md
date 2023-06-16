# Getting Started with Create React App

This project was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).

## Available Scripts

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.

The page will reload when you make changes.\
You may also see any lint errors in the console.

### `npm test`

- unit 단위 테스트를 할수있다.

### `npm run build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.\
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

- 최적화된 프로덕션 빌드를 생성한다. (프로덕션 배포시 만들어지는 파일 3개)

    - main.716e730f.js
    - 787.2843ca88.chunk.js
    - main.073c9b0a.css 


# React의 파일 구조

---------

- package.json  
    의존성을 정의하는 파일 (pom.xml이랑 비슷함)

- node_modules  
    다운로드된 의존성들이 존재함

- React Initialization  

    - public/index.html   
        브라우저에서 가장 먼저 로딩되는 페이지

    - src/index.js   

        index.html에서 불러와지는 js  
        React 앱을 초기화하고, 앱 컴포넌트를 로드한다. 

        ~~~react
        const root = ReactDOM.createRoot(document.getElementById('root')); //루트로 이동해서
                root.render(
                <React.StrictMode>
                    <App /> //앱 컴포넌트를 로드한다.
                </React.StrictMode>
                );
        ~~~

    - src/App.js   
        앱 컴포넌트의 Code

        - src/App.css   
            앱 컴포넌트 스타일링

        - src/App.test.js  
            앱 컴포넌트의 단위 테스트 파일


# React Component

------

- React에서 처음으로 로드되는 컴포넌트는 앱 컴포넌트이다.