from pprint import pprint
import spacy

TEXTS = [
    'S. flavida decreased G0/G1 arrest and facilitated the G2/M transition of NIH3T3s, also downregulated the expression of p38, p53, p16, and the related inflammatory mediators',
    'S. flavida potentially modulated senescence-associated hallmarks in fibroblasts exposed to H2 O2 , thus it may inhibit the aging process via controlling the OS',
]


def main(model='en_core_web_sm'):
    print("Start app")
    nlp = spacy.load(model)
    print("Loaded model '%s'" % model)
    print("Processing %d texts" % len(TEXTS))

    for text in TEXTS:
        doc = nlp(text)
        relations = extract_relations(doc)
        for r1, r2, r3 in relations:
            print('{:<10}\t{}\t{}'.format(r1.text, r3, r2.lemma_))
            # print('{:<10}\t{}\t{}'.format(r1.text, r3, r2.text))

def extract_relations(doc):
    # merge entities and noun chunks into one token
    # TODO: Why removing this stops the method from functioning
    spans = list(doc.ents) + list(doc.noun_chunks)
    for span in spans:
        span.merge()

    relations = []
    # for token in filter(lambda w: str(w) == "S. flavida", doc):
    for token in doc:
        if token.dep_ == 'nsubj':
            relations.append((token.head.right_edge, token.head, token))
    return relations

if __name__ == "__main__":
    main()