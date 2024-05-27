import React, {useState, useEffect} from 'react';
import axios from "axios";

function File() {
    const [imgBase64, setImgBase64] = useState([]);
    const [imgFile, setImgFile] = useState(null);


    const handleChangeFile = (event) => {
        console.log(event.target.files);
        setImgFile(event.target.files);
        setImgBase64([]);
        for(let i=0 ; i<event.target.files.length ; i++) {
            if(event.target.files[i]) {
                let reader = new FileReader();
                reader.readAsDataURL(event.target.files[i]);
                reader.onloadend = () => {
                    const base64 = reader.result; // 비트맵 데이터 리턴, 이 데이터를 통해 파일 미리보기가 가능함
                    console.log(base64)
                    if(base64) {
                        let base64Sub = base64.toString()
                        setImgBase64(imgBase64 => [...imgBase64, base64Sub]);
                    }
                }
            }
        }
    }

    const WriteBoard = () => {

        const fd = new FormData();
        for(let i=0 ; i<imgFile.length ; i++) {
            fd.append("file", imgFile[i]);
        }
        fd.append(
            "comment",
            "hello world"
        );
        axios.post("http://localhost:8080/image", fd)
            .then(response => {
                if(response.data) {
                    console.log(response.data)
                    setImgFile(null);
                    setImgBase64([]);
                    alert("업로드 완료!");
                }
            })
            .catch((error) => {
                console.log(error)
                alert("실패!");
            })
    }


    return (
        <div>
            <h2>사진 업로드</h2>
            <input type="file" id="file" onChange={handleChangeFile} multiple/>
            <h3>업로드 한 사진 미리보기</h3>
            {imgBase64.map((item) => {
                return (
                    <img
                        key={item}
                        src={item}
                        alt={"First slide"}
                        style={{width:"200px", height:"150px"}}
                    />
                )
            })}
            <button onClick={WriteBoard} style={{border: '2px solid black'}}>이미지 업로드</button>
        </div>
    );
}

export default File;