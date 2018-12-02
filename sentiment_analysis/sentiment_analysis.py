# This script follows https://towardsdatascience.com/sentiment-analysis-with-python-part-1-5ce197074184
#

# TODO:
# Support N-grams in Vectorization
# Currently the regression tokenizes the word. Phrases made of more than one word would have less impact.
# See #tokenizer #preprocessor https://scikit-learn.org/stable/modules/generated/sklearn.feature_extraction.text.CountVectorizer.html
#
# Spike SVM
from pprint import pprint
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import accuracy_score
from sklearn.model_selection import train_test_split

file_name = "../train"

# Memoize file content
statements_train = []
for line in open(file_name):
    statements_train.append(line.strip())

# pprint(statements_train)

# Vectorization
cv = CountVectorizer(binary=True)
cv.fit(statements_train)
X = cv.transform(statements_train)
X_test = cv.transform(statements_train)


# Build classifier via Linear Regression
# The first n is positive(reduces), the rest is negative(increases)
target = [1 if i < 28 else 0 for i in range(47)]

X_train, X_val, y_train, y_val = train_test_split(
    X, target, train_size = 0.75
)

# This is to find out which C gives highest accuracy
# for c in [0.01, 0.05, 0.25, 0.5, 1]:
#     lr = LogisticRegression(C=c)
#     lr.fit(X_train, y_train)
#     print ("Accuracy for C=%s: %s"
#            % (c, accuracy_score(y_val, lr.predict(X_val))))

# Regression
final_model = LogisticRegression(C=0.25)
final_model.fit(X, target)
pprint ("Final Accuracy: %s"
       % accuracy_score(target, final_model.predict(X_test)))



feature_to_coef = {
    word: coef for word, coef in zip(
        cv.get_feature_names(), final_model.coef_[0]
    )
}


for best_positive in sorted(
    feature_to_coef.items(),
    key=lambda x: x[1],
    reverse=True)[:]:
    print (best_positive)



# for best_negative in sorted(
#     feature_to_coef.items(),
#     key=lambda x: x[1])[:5]:
#     print (best_negative)