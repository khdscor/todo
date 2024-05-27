import React, {useState, useEffect} from 'react';
import axios from "axios";

function File() {
    const [imgFile, setImgFile] = useState(null);
    useEffect(() => {
        readImages();
    }, [])
    
    const readImages = async () => {
        return axios.get("http://localhost:8080/find/files" )
           .then(response => {
                setImgFile(response.data);
        });
    }

    const downloadFile =  (filename) => {
    const url = `http://localhost:8080/download/${filename}`;

    // 파일 다운로드 링크 생성
    const downloadLink = document.createElement('a');
    downloadLink.href = url;
    downloadLink.setAttribute('download', filename);
    downloadLink.click();
    }
    
    return (
        <div>
            <h2>사진 목록</h2>
            {imgFile ? imgFile.map((item) => {
                return (
                    <div key={item.pid}>
                        <img
                            src={"http://localhost:8080/images/" + item.filename}
                            alt={`img${item.filename}`}
                            style={{width:"200px", height:"150px"}}
                            onError={(e) => {
                                e.target.src = "/noImage.png"; // 이미지 로딩 실패 시 기본 이미지로 교체
                            }}
                        />
                        <p>{item.filename}</p>
                        <button onClick={() => downloadFile(item.filename)}>다운로드</button>
                    </div>
                )
            }) : ""}
        </div>
    );
}

export default File;