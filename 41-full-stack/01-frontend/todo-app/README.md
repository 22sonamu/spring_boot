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



# 함수 컴포넌트 / 클래스 컴포넌트

-----

## State

~~~
특정 컴포넌트에 대한 데이터
~~~

- 리액트 초기 버전에선 클래스 컴포넌트만 State를 가질 수 있었다.
- React Hooks의 등장으로, 함수 컴포넌에서도 State를 사용할 수 있게 되었다.
- 좀더 간결한 함수형 컴포넌트를 추천한다.

# JSX (JavaScript XML)

-------

~~~
React에서 사용하는 JavaScript를 확장한 문법
~~~

- HTML보다 엄격하다.
- 닫는태그 필수 , 최상위 태그는 한가지만 있어야함



- 브라우저마다 최신 기능에 대한 지원 수준이 다르다.
    
    - JS 코드에 대한 호환성을 어떻게 보장할까?
        
        - Solution : Babel 
            - **JSX를 JS로 변환함**
            - 최신 JS와 오래된 브라우저 간의 호환을 보장

- JSX

    - () : 복잡한 JSX 값을 반환할때 감싼다.
    ~~~JSX
    function SecondComponent(){
        return (
            <div className="SecondComponent">Second Component</div>
            );
        }
    ~~~

    - Component는 대문자로 시작해야한다.

    - HTML에는 소문자를 사용한다 (ex. div)

# State

~~~
리액트의 내장 객체로써, 컴포넌트의 데이터를 저장하는데 사용된다.
~~~

### userState


- State 값
- State 값을 업데이트 하는 함수


### React의 background

- HTML에선 DOM안의 값이 바뀌면, 프론트에 표시해주기위해 DOM을 업데이트하는 코드를 따로 작성해야했다.
- React

    - 페이지가 로딩되면, 이에대한 첫번째 버전의 가상 DOM을 생성한다.
    - 상태가 업데이트되면, 해당 컴포넌트는 다시 랜더링하고 두번째 버전의 가상 DOM을 생성한다.
    - 리액트는 DOM V1 과 DOM V2를 비교해 차이점을 파악하고, 달라진 부분을 HTML페이지에 적용한다.