# InformedPriors
_An AI-Powered Disease Pathway Research Tool_  
**by Team HGT**

## Introduction
InformedPriors is an AI-powered disease pathway research tool. InformedPriors uses NLP to extract the causal relationships which exist in medical literature to construct an extensive knowledge graph. Queries to InformedPriors use inference chains in the knowledge graph to reconstruct the complex causal pathways which exist for a disease or other type of entity in medical ontology, creating a list of all the dependencies for the disease with values which can be used as an informed prior for data models.

## Benefits
- Accelerates the discovery process for testable hypothesis
- Identifies feedback loops useful to complex diseases
- Automatically adjusts disease pathways to latest research

## Model Components
- PubMed Abstracts (DataSet)
- Manually Labeled POS (Training Set)
- Spacy (Data Pre-Processing)
- AWS Comprehend Medical (Name Entity Recognition)
- SciKit Logistic Regressor (Sentiment Analyzer)
- Neo4j (Graph Database & Query Engine)

## Prior Work
- UniProt
- KEGG
- Comparative Toxicogenomics Database
- ConsensusPathDB
- Literature-Derived Human Gene-Disease Network
- BeFree
- Gene Disease Associations Database

## Requirements
- 

## Collaboration Notes
The Git and Docker repos contain all the configuration information required for collaboration except access tokens. To synchronize access tokens across multiple devices, platforms and users without losing local control, you can use LastPass, an encrypted email platform such as ProtonMail or smoke signals. If you use any AWS services, use AWS IAM to assign user permissions and create keys for each collaborator individually.

Collaborators are required to install all service dependencies on their local device if they wish to test code on their localhost. A collaborate should always **FORK** the repo from the main master and fetch changes from the upstream repo so reality is controlled by one admin responsible for approving all changes. New dependencies should be added to the Dockerfile, **NOT** to the repo files. Collaborators should test changes to Dockerfile locally before making a pull request to merge any new dependencies:

```shell
docker build -t test-image .
```

## VCS Ignore
.gitignore and .dockerignore have already been installed with the standard exclusions. To prevent unintended file proliferation through version control & provisioning, add/edit .gitignore and .dockerignore to include all new:

1. local environments folders
2. localhost dependencies
3. configuration files with credentials and local variables