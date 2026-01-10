# データ要素市場供給側管理システム - バックエンド

英語のバージョンは最新です：[English](README.md)

**フロントエンドのリポジトリはこちら:** [data-element-frontend](https://github.com/yexca/data-element-frontend)

---

## 概要

本プロジェクトは、第四次産業革命と生成AIの発展に伴い急増するデータに対応するため、データ要素市場の「供給側」に焦点を当てた管理システムです。

従来の集中型データ管理の課題に対し、**SpringBoot**、**Elasticsearch**、そしてWeb3の基盤技術である**ブロックチェーン（Fisco Bcos）**を革新的に統合しました。これにより、効率的なデータ検索機能と、データの真正性・所有権を保証する改ざん不可能な記録管理を実現しています。

## 🌟 主な機能

* **RESTful API**: Apifoxで設計・管理された、フロントエンドと通信するための標準化されたAPI群。
* **認証と認可**: JWT (JSON Web Token) に基づく認証システムと、インターセプターによるリクエスト検証。
* **ロールベースアクセスコントロール (RBAC)**: 「個人/企業ユーザー」および「従業員/管理者/スーパー管理者」の複数ロールに対応した、きめ細やかなアクセス制御。
* **データ検索**: Elasticsearchを導入し、データ製品の高速な全文検索機能を提供（`ik_max_word` 中文分詞器利用）。
* **ブロックチェーン連携**: ユーザー登録情報やデータ製品のメタデータをFisco Bcosブロックチェーンに記録し、データの所有権と履歴の不変性（Immutability）を担保。
* **非構造化データ管理**: ユーザーがアップロードするデータ製品ファイル（またはサンプル）をAliyun OSS (Object Storage Service) で管理。
* **堅牢なアーキテクチャ**: Controller, Service, Mapper（データアクセス層）による明確な責務分離を行ったレイヤードアーキテクチャを採用。

## 🛠 使用技術

| カテゴリ               | 技術                           |
| :--------------------- | :----------------------------- |
| コア                   | Java 17, Spring Boot           |
| フレームワーク         | Spring MVC, MyBatis            |
| データベース           | MySQL 8.2                      |
| 検索エンジン           | Elasticsearch 7.12.1           |
| ブロックチェーン       | Fisco Bcos                     |
| オブジェクトストレージ | Aliyun OSS                     |
| 認証                   | JWT (JSON Web Token)           |
| ビルド・デプロイ       | Maven, Docker / Docker Compose |

## 起動方法

1. **リポジトリをクローン:**

   ```bash
   git clone https://github.com/yexca/data-element-backend.git
   cd data-element-backend
   ```

2. **依存関係のセットアップ:**
   本システムの実行には、MySQL, Elasticsearch, Fisco Bcos, Aliyun OSS の各サービスが動作している必要があります。

3. **設定ファイルの構成:**
   `src/main/resources/application.yml` (および `application-prod.yml`) を開き、各サービス（データベース接続情報、OSSキー、Elasticsearchアドレス等）に合わせて設定を更新してください。

4. **Mavenによる実行:**

   ```bash
   mvn spring-boot:run
   ```

5. **Dockerによる実行 (推奨):**
   MySQLやElasticsearchと共にコンテナとして起動することが可能です。

   ```bash
   docker build -t yexca/data-element:v1.1 .
   ```
