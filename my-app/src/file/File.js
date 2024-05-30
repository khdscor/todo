import React, {useState} from 'react';
import { Link } from 'react-router-dom';
import './File.css';
import axios from "axios";

function File() {
    const [files, setFiles] = useState(null);

    const handleChangeFile = (event) => {
        setFiles(event.target.files);
    };
    
    const handleButtonClick = () => {
        document.getElementById('fileInput').click();
    };

    const handleClearFiles = () => {
        setFiles(null);
    };

    const uploadFile = () => {
        //파일이 한 개 이상 있을 경우
        if(files){
            const body = new FormData();
            // Post 요청에 함께 보낼 Body 작성(입력한 파일 추가)
            for(let i=0 ; i< files.length ; i++) {
                body.append("file", files[i]);
            }
            // 서버(Spring boot)로 요청(업로드)
            axios.post("http://localhost:8080/file/upload", body)
                .then(response => {
                    if(response.data) {
                        setFiles(null);
                        alert("업로드 완료!");
                    }
                })
                .catch((error) => {
                    alert("실패!");
                })
        } 
        // 입력한 파일이 한 개도 없을 경우 
        else{
            alert("파일을 1개 이상 넣어주세요!")
        }
    }


    return (
        <div className="container">
            {/* 이전에 업로드했던 파일들 확인 */}
            <Link to={`/readfile`} className="link">
                <h2 className="heading">업로드한 파일 보기</h2>
            </Link>
            {/* 업로드할 파일 선택, 여러개 가능 */}
            <input
                type="file"
                id="fileInput"
                onChange={handleChangeFile}
                multiple
                style={{ display: 'none' }}
            />
            <button onClick={handleButtonClick} className="button">
                파일 선택
            </button>
            {/* 선택한 파일 이름 확인 */}
            {files && (files.length >= 1 ) ? (
                <div className="file-list">
                <ul>
                    {Array.from(files).map((file, index) => (
                    <li key={index}>{file.name}</li>
                    ))}
                </ul>
                <button onClick={handleClearFiles} className="button">
                    선택된 파일 지우기
                </button>
                </div>
            ) : (
                <p>선택된 파일 없음</p>
            )}
            {/* 파일 업로드 버튼 */}
            <button onClick={uploadFile} className="upload-button">
                파일 업로드
            </button>
        </div>
    );
}

export default File;