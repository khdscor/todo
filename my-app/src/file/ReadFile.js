import React, {useState, useEffect} from 'react';
import axios from "axios";
import './ReadFile.css';

function File() {
    const [files, setFiles] = useState(null);
    useEffect(() => {
        readFiles();
    }, [])
    
    const readFiles = async () => {
        return axios.get("http://localhost:8080/file/find/all" )
           .then(response => {
                setFiles(response.data);
        });
    }

    const downloadFile =  (filename) => {
    const url = `http://localhost:8080/file/download/${filename}`;

    // 파일 다운로드 링크 생성
    const downloadLink = document.createElement('a');
    downloadLink.href = url;
    downloadLink.setAttribute('download', filename);
    downloadLink.click();
    }
    
    return (
        <div className="file-list-container">
            <h2 className="title">파일 목록</h2>
            <div className="file-list">
                {/* 서버에 업로드된 파일들을 가져온 후 화면에 표시(이름, 이미지) */}
                {files ? files.map((item) => (
                <div key={item.pid} className="file-item">
                    <img
                    // src는 정적 리소스 경로
                    src={`http://localhost:8080/file/${item.filename}`}
                    alt={`img${item.filename}`}
                    className="file-image"
                    // 이미지 파일이 아닐 시 기본 이미지로 표시
                    onError={(e) => {
                        e.target.src = "/noImage.png"; 
                    }}
                    />
                    <p className="file-name">{item.filename}</p>
                    {/* 파일 별 다운로드 버튼 노출, 클릭 시 다운로드 진행 */}
                    <button onClick={() => downloadFile(item.filename)} className="button download-button">
                    다운로드
                    </button>
                </div>
                )) : <p>파일이 없습니다.</p>}
            </div>
        </div>
    );
}

export default File;