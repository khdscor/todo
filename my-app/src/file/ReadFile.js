import React, {useState, useEffect} from 'react';
import axios from "axios";

function File() {
    const [imgBase64, setImgBase64] = useState([]);
    const [imgFile, setImgFile] = useState(null);
    console.log(imgFile)
    useEffect(() => {
        readImages();
    }, [])
    
    const readImages = async () => {
        return axios.get("http://localhost:8080/find/files" )
           .then(response => {
                console.log('======= 이미지 목록 조회 성공 =======')
                setImgFile(response.data);
                console.log(response.data);
        });
    }

    return (
        <div>
            <h2>사진 목록</h2>
            {imgFile ? imgFile.map((item) => {
                return (
                    <div key={item.pid}>
                        <img
                            src={"http://localhost:8080/images/"+item.filename}
                            alt={"img"+item.filename}
                            style={{width:"200px", height:"150px"}}
                            onError={(e) => {
                                e.target.src = "/noImage.png"; // 이미지 로딩 실패 시 기본 이미지로 교체
                            }}
                        />
                        <p>{item.filename}</p>
                    </div>
                )
            }) : ""}
        </div>
    );
}

export default File;