import React, {useState, useEffect} from 'react';
import axios from "axios";

function File() {
    const [imgBase64, setImgBase64] = useState([]);
    const [imgFile, setImgFile] = useState(null);

    const handleChangeFile = (event) => {
        setImgFile(event.target.files);
        setImgBase64([]);
        for(let i=0 ; i<event.target.files.length ; i++) {
            if(event.target.files[i]) {
                let reader = new FileReader();
                reader.readAsDataURL(event.target.files[i]);
                reader.onloadend = () => {
                    const base64 = reader.result; // 비트맵 데이터 리턴, 이 데이터를 통해 파일 미리보기가 가능함
                    if (base64) {
                        // 이미지 파일인지 여부를 확인
                        if (event.target.files[i].type.startsWith('image/')) {
                            let base64Sub = base64.toString();
                            setImgBase64((imgBase64) => [...imgBase64, {name: event.target.files[i].name, src: base64Sub}]);
                        } else {
                            // 이미지 파일이 아니면 기본 이미지 경로를 사용
                            setImgBase64((imgBase64) => [...imgBase64, {name: event.target.files[i].name, src: "/noImage.png"}]);
                        }
                    }
                }
            }
        }
    };
    
    const handleButtonClick = () => {
    document.getElementById('fileInput').click();
    };

    const handleClearFiles = () => {
    setImgFile(null);
    setImgBase64([]);
    document.getElementById('fileInput').value = null;
    };

    const WriteBoard = () => {
        if(imgFile){
            const fd = new FormData();
            for(let i=0 ; i<imgFile.length ; i++) {
                fd.append("file", imgFile[i]);
            }
            axios.post("http://localhost:8080/image", fd)
                .then(response => {
                    if(response.data) {
                        setImgFile(null);
                        setImgBase64([]);
                        alert("업로드 완료!");
                    }
                })
                .catch((error) => {
                    alert("실패!");
                })
        } else{
            alert("파일을 1개 이상 넣어주세요!")
        }
    }


    return (
        <div>
            <h2>사진 업로드</h2>
            <input type="file" id="fileInput" onChange={handleChangeFile} multiple  style={{ display: 'none' }}/>
            <button onClick={handleButtonClick}>파일 선택</button>
            {imgFile ? (
                <div>
                <ul>
                    {Array.from(imgFile).map((file, index) => (
                    <li key={index}>{file.name}</li>
                    ))}
                </ul>
                <button onClick={handleClearFiles}>선택된 파일 지우기</button>
                </div>
            ) : (
                <p>선택된 파일 없음</p>
            )}
            <h3>업로드 한 사진 미리보기</h3>
            {imgBase64.map((item, index) => {
                return (
                    <div key={index}>
                        <img
                            src={item.src}
                            alt={"First slide"}
                            style={{width:"200px", height:"150px"}}
                        />
                        <p>{item.name}</p>
                    </div>
                )
            })}
            <button onClick={WriteBoard} style={{border: '2px solid black'}}>이미지 업로드</button>
        </div>
    );
}

export default File;