# 🚀 PDFTextSearch — Full-text Search on PDF Documents
**Spring Boot + Apache Tika + Elasticsearch**

PDFTextSearch is a backend service built with Spring Boot that allows users to upload PDF documents, extract their textual content using Apache Tika, and index the extracted text into Elasticsearch to provide fast full-text search functionality.

This system enables **Google-like keyword searching** across the contents of PDF files.

---

## 📌 Project Summary

| Feature                    | Description                                  |
|---------------------------|----------------------------------------------|
| 📄 PDF Upload             | Upload and store PDF files                   |
| ✂️ Text Extraction        | Extract text using Apache Tika               |
| 🔎 Elasticsearch Indexing | Store indexed text for full-text search      |
| 🔍 Full-Text Search       | Search inside PDF contents by keywords       |
| ⚡ Async Processing       | Upload → extract → index runs asynchronously |
| 📑 Pagination             | Paginated search results                     |
| 🗄 PostgreSQL             | Store PDF metadata                           |

---

## ✔️ Requirements Implemented

| Requirement             | Description                                 | Status          |
|-------------------------|---------------------------------------------|-----------------|
| PDF Upload              | Accept PDF files via REST API              | ✅ Implemented  |
| Text Extraction         | Use Apache Tika to read PDF content        | ✅ Implemented  |
| Elasticsearch Indexing  | Save extracted text to ES index            | ✅ Implemented  |
| Full-Text Search        | Query PDF contents using search API        | ✅ Implemented  |
| Pagination              | Paginated search responses                 | ✅ Implemented  |
| Async Processing        | PDF content processing is asynchronous     | ✅ Implemented  |
| Elasticsearch Client    | Integrated via Spring Data Elasticsearch   | ✅ Implemented  |
| PostgreSQL Storage      | Store document metadata                    | ✅ Implemented  |

---

## 🛠️ Tech Stack

| Technology               | Purpose                      |
|-------------------------|------------------------------|
| Java 17+                | Backend                      |
| Spring Boot 3.x         | REST API                     |
| PostgreSQL              | Store PDF metadata           |
| Apache Tika             | Extract text from PDFs       |
| Elasticsearch 9.x       | Full-text search engine      |
| Spring Data Elasticsearch | Elasticsearch integration |
| MapStruct               | Mapping DTO ↔ Entity         |
| Lombok                  | Reduce boilerplate code      |
| Maven                   | Build tool                   |

---

## 🏗️ System Architecture

```text
+---------------------------+
|        Client / UI        |
+-------------+-------------+
              |
              ▼
+---------------------------+
|      Spring Boot API      |
+-------------+-------------+
              |
    +------------------+------------------+
    |                                     |
    ▼                                     ▼
+--------------------+          +------------------------+
|     File Storage   |          |   Apache Tika Engine   |
|       (uploads/)   |          |    (Text Extraction)   |
+--------------------+          +------------------------+
              |
              ▼
+--------------------------+
|   Elasticsearch Index    |
+--------------------------+
              |
              ▼
+--------------------------+
|     Search API (ES)      |
+--------------------------+



## 📂 Project Structure

```
src/main/java/az/devlab/pdftextsearch
│
├── config/
├── controller/
├── dto/
├── exception/
├── mapper/
├── model/
│   └── document/
├── repository/
├── service/
├── serviceimpl/
└── util/
```

---

## 🔧 Setup Instructions

### **1️⃣ Start Elasticsearch**
```bash
cd elasticsearch-9.x/bin
.\elasticsearch.bat
```

Verify in browser:
https://localhost:9200

---

### **2️⃣ Configure PostgreSQL**

`application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=12345
```

---

### **3️⃣ Run Backend**

**Via IntelliJ IDEA:**  
Run `PdfTextSearchApplication`

**Via Maven:**
```bash
mvn spring-boot:run
```

---

## 📤 API — Upload PDF

**POST** `/api/v1/pdfs/upload`  
**Body:** multipart/form-data

| Key  | Type | Value         |
|------|------|---------------|
| file | File | PDF document  |

**Sample Response:**
```json
{
  "documentId": 1,
  "fileName": "example.pdf",
  "fileSize": 151474,
  "indexed": false,
  "uploadedAt": "2025-11-20T13:57:07",
  "message": "PDF uploaded successfully. Indexing continues asynchronously."
}
```

---

## 🔎 API — Full-Text Search

**POST** `/api/v1/search`

**Request Body:**
```json
{
  "query": "backend",
  "page": 0,
  "size": 10
}
```

**Response Example:**
```json
{
  "content": [
    {
      "id": "es_12345",
      "documentId": 1,
      "fileName": "example.pdf",
      "snippet": "... backend developer experience ...",
      "fileSize": 151474,
      "uploadedAt": "2025-11-20T13:57:07",
      "score": 1.42
    }
  ],
  "page": 0,
  "size": 10,
  "totalElements": 1,
  "totalPages": 1
}
```

---

## ⚡ Bonus Features (Completed)

| Feature           | Description                                   | Status |
|-------------------|-----------------------------------------------|--------|
| Async Processing  | PDF extraction + indexing in background        | ✔️     |
| Pagination        | Paginated search results                      | ✔️     |

---

## 📝 Conclusion

PDFTextSearch is a fully functional backend service that:

- Extracts text from PDF files
- Indexes content into Elasticsearch
- Supports full-text search
- Handles large files asynchronously
- Stores metadata in PostgreSQL

✔️ **All project requirements have been fully implemented.**

---

📬 Contact

Made with  by Xadija Pashayeva

📧 Email: xadijapashayeva@gmail.com

🔗 LinkedIn: https://www.linkedin.com/in/xadija-pashayeva/in/xadija-pashayeva
